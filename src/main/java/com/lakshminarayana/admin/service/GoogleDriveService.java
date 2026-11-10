package com.lakshminarayana.admin.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;

@Service
public class GoogleDriveService {

    private static final Logger log = LoggerFactory.getLogger(GoogleDriveService.class);
    private static final String APPLICATION_NAME = "Lakshmi Narayana Travels - Google Drive Engine";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Value("${google.sheets.enabled:false}")
    private boolean enabled;

    @Value("${google.sheets.credentialsPath:credentials.json}")
    private String credentialsPath;

    @Value("${google.drive.folderId:}")
    private String folderId;

    @Value("${data.uploads.dir:./uploads}")
    private String uploadsDir;

    private Drive driveService;
    private boolean initialized = false;

    private synchronized void initDrive() {
        if (initialized) return;
        if (!enabled) return;

        try {
            java.io.File credFile = new java.io.File(credentialsPath);
            if (!credFile.exists()) {
                java.io.File rootCred = new java.io.File("../" + credentialsPath);
                if (rootCred.exists()) credFile = rootCred;
            }

            if (!credFile.exists() || credFile.length() == 0) return;

            try (InputStream in = new FileInputStream(credFile)) {
                GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                        .createScoped(Collections.singletonList(DriveScopes.DRIVE_FILE));

                final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                this.driveService = new Drive.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

                this.initialized = true;
                log.info("Admin successfully connected to Google Drive API!");
            }
        } catch (Exception e) {
            log.error("Failed to initialize Google Drive service: {}", e.getMessage());
            this.initialized = false;
        }
    }

    public boolean isConnected() {
        if (!initialized) initDrive();
        return initialized && driveService != null;
    }

    public String uploadCarImageOrDoc(String fileName, String mimeType, byte[] data) {
        // If Google Drive is connected, upload to Drive
        if (isConnected()) {
            try {
                File fileMetadata = new File();
                fileMetadata.setName("LNT-" + UUID.randomUUID() + "-" + fileName);
                if (folderId != null && !folderId.isBlank()) {
                    fileMetadata.setParents(Collections.singletonList(folderId));
                }

                java.io.File tempFile = java.io.File.createTempFile("lnt-upload-", fileName);
                Files.write(tempFile.toPath(), data);

                FileContent mediaContent = new FileContent(mimeType != null ? mimeType : "image/jpeg", tempFile);
                File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                        .setFields("id, webViewLink, webContentLink")
                        .execute();

                tempFile.delete();
                log.info("Uploaded to Google Drive: {} (ID: {})", fileName, uploadedFile.getId());
                return uploadedFile.getWebViewLink() != null ? uploadedFile.getWebViewLink() : "https://drive.google.com/file/d/" + uploadedFile.getId() + "/view";
            } catch (Exception e) {
                log.warn("Google Drive upload failed, falling back to local shared uploads: {}", e.getMessage());
            }
        }

        // Local fallback storage in uploads/
        try {
            String ext = "";
            int dot = fileName.lastIndexOf('.');
            if (dot > 0) ext = fileName.substring(dot);
            String savedName = UUID.randomUUID().toString() + ext;
            Path uploadPath = Paths.get(uploadsDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path target = uploadPath.resolve(savedName);
            Files.write(target, data);
            return "/uploads/" + savedName;
        } catch (Exception e) {
            log.error("Error saving local file: {}", e.getMessage());
            return "https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=800&q=80";
        }
    }
}

