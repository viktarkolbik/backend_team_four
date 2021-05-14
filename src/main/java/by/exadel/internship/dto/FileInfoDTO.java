package by.exadel.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDTO {
    String fileName;
    ByteArrayResource resource;
}
