package com.lakshminarayana.admin.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleSheetsService {

    private static final Logger log = LoggerFactory.getLogger(GoogleSheetsService.class);
    private static final String APPLICATION_NAME = "Lakshmi Narayana Travels - Admin Google Sheets Engine";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Value("${google.sheets.enabled:false}")
    private boolean enabled;

    @Value("${google.sheets.spreadsheetId:}")
    private String spreadsheetId;

    @Value("${google.sheets.credentialsPath:credentials.json}")
    private String credentialsPath;

    private Sheets sheetsService;
    private boolean initialized = false;

    private synchronized void initSheets() {
        if (initialized) return;
        if (!enabled) {
            log.info("Google Sheets integration is currently disabled (running with Local Fallback Engine).");
            return;
        }

        try {
            File credFile = new File(credentialsPath);
            if (!credFile.exists()) {
                File rootCred = new File("../" + credentialsPath);
                if (rootCred.exists()) {
                    credFile = rootCred;
                }
            }

            if (!credFile.exists() || credFile.length() == 0) {
                log.warn("Google Sheets credentials file '{}' not found. Using local fallback data store.", credentialsPath);
                return;
            }

            try (InputStream in = new FileInputStream(credFile)) {
                GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                        .createScoped(Collections.singletonList(SheetsScopes.SPREADSHEETS));

                final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                this.sheetsService = new Sheets.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

                this.initialized = true;
                log.info("Admin successfully connected to Google Sheets API! Spreadsheet ID: {}", spreadsheetId);
            }
        } catch (Exception e) {
            log.error("Failed to initialize Google Sheets service: {}. Falling back to local storage.", e.getMessage());
            this.initialized = false;
        }
    }

    public boolean isConnected() {
        if (!initialized) {
            initSheets();
        }
        return initialized && sheetsService != null && spreadsheetId != null && !spreadsheetId.isBlank();
    }

    public List<List<Object>> readSheet(String range) {
        if (!isConnected()) return null;
        try {
            ValueRange response = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            return response.getValues();
        } catch (Exception e) {
            log.error("Error reading Google Sheet range {}: {}", range, e.getMessage());
            return null;
        }
    }

    public boolean appendRow(String range, List<Object> rowValues) {
        if (!isConnected()) return false;
        try {
            ValueRange body = new ValueRange().setValues(Collections.singletonList(rowValues));
            sheetsService.spreadsheets().values()
                    .append(spreadsheetId, range, body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
            return true;
        } catch (Exception e) {
            log.error("Error appending row to Google Sheet {}: {}", range, e.getMessage());
            return false;
        }
    }

    public boolean updateSheetRange(String range, List<List<Object>> values) {
        if (!isConnected()) return false;
        try {
            ValueRange body = new ValueRange().setValues(values);
            sheetsService.spreadsheets().values()
                    .update(spreadsheetId, range, body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
            return true;
        } catch (Exception e) {
            log.error("Error updating range in Google Sheet {}: {}", range, e.getMessage());
            return false;
        }
    }

    public String getSpreadsheetId() { return spreadsheetId; }
    public boolean isEnabled() { return enabled; }
    public String getCredentialsPath() { return credentialsPath; }
}

