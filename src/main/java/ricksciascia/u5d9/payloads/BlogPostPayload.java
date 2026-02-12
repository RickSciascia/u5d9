package ricksciascia.u5d9.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlogPostPayload {
    private String category;
    private String title;
    private String content;
    private int tempoDiLettura;
    private long authorId;
}
