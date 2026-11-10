# ============================================================
#  Lakshmi Narayana Travels — Admin Portal
#  Multi-stage Docker build  |  Java 21 + Spring Boot 3.3.4
# ============================================================

# ── Stage 1: Build ───────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copy Maven wrapper first (better layer caching)
COPY .mvn/  .mvn/
COPY mvnw   pom.xml ./
RUN chmod +x ./mvnw

# Download all dependencies (cached layer unless pom.xml changes)
RUN ./mvnw dependency:go-offline -B --no-transfer-progress

# Copy source and build the fat JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests -B --no-transfer-progress

# ── Stage 2: Runtime ─────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="Lakshmi Narayana Travels"
LABEL description="Admin Portal – Fleet & Booking Management"

WORKDIR /app

# The app uses hardcoded seed data (DataSeeder class) — no external files needed.
# All data lives in-memory; Google Sheets provides production persistence.

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# ── Environment variables (all overridable at runtime) ───────
# PORT is injected by Render automatically; fallback = 8082
ENV PORT=8082
ENV UPLOADS_DIR=/app/uploads
ENV GOOGLE_SHEETS_ENABLED=false
ENV GOOGLE_SHEETS_SPREADSHEET_ID=
ENV GOOGLE_SHEETS_CREDENTIALS_PATH=/app/credentials.json
ENV GOOGLE_DRIVE_FOLDER_ID=
ENV JAVA_OPTS="-Xms256m -Xmx512m"

EXPOSE 8082

ENTRYPOINT ["sh", "-c", \
  "java $JAVA_OPTS \
   -Dserver.port=$PORT \
   -Ddata.uploads.dir=$UPLOADS_DIR \
   -Dgoogle.sheets.enabled=$GOOGLE_SHEETS_ENABLED \
   -Dgoogle.sheets.spreadsheetId=$GOOGLE_SHEETS_SPREADSHEET_ID \
   -Dgoogle.sheets.credentialsPath=$GOOGLE_SHEETS_CREDENTIALS_PATH \
   -Dgoogle.drive.folderId=$GOOGLE_DRIVE_FOLDER_ID \
   -jar /app/app.jar"]

