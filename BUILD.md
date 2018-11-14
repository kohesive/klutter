# Build Kohesive

./gradlew clean build

# Upload to repositories

./gradlew clean build uploadArhives bintrayUpload

# Publish after safely in repositories

# is manual: ./gradlew closeAndReleaseRepository 
./gradlew bintrayPublish
