package roon.study.unittesting.ch6.audit_example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "ch6.audit")
public class AuditProperties {
    private int maxEntriesPerFile;
    private String directoryName;
}
