package pl.teo.azuredemoapp.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.teo.azuredemoapp.AzureBlobProperties;
import pl.teo.azuredemoapp.model.FileMetadata;
import pl.teo.azuredemoapp.repository.FileMetadataRepo;
import pl.teo.azuredemoapp.service.FileService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureFileService implements FileService {
    private final FileMetadataRepo fileMetadataRepo;
    private final AzureBlobProperties blobProperties;

    public AzureFileService(FileMetadataRepo fileMetadataRepo, AzureBlobProperties blobProperties) {
        this.fileMetadataRepo = fileMetadataRepo;
        this.blobProperties = blobProperties;
    }

    @Override
    public FileMetadata upload(MultipartFile imageFile) {
        UUID uuid = UUID.randomUUID();
        String filename = "image_" + uuid.toString() + imageFile.getOriginalFilename();
        BlobClient blobClient = containerClient().getBlobClient(filename);
        try {
            blobClient.upload(imageFile.getInputStream(), imageFile.getSize());
            return fileMetadataRepo.save(new FileMetadata(imageFile, filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InputStream download(String name) {
        BlobContainerClient containerClient = containerClient();
        BlobClient blobClient = containerClient.getBlobClient(name);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        blobClient.download(os);

        return new ByteArrayInputStream(os.toByteArray());
    }


    private BlobContainerClient containerClient() {
        String connectionString = blobProperties.getConnectionString();
        String containerName = blobProperties.getContainer();

        BlobServiceClient serviceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient container = serviceClient.getBlobContainerClient(containerName);
        return container;
    }
}
