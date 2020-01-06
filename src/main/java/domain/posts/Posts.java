package domain.posts;

import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;

    @Column(length = 400, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contetns;

    @Column(length = 400, nullable = false)
    private  String author;

    @Builder
    public Posts(String title, String contetns, String author) {
        title = title;
        contetns = contetns;
        author = author;
    }
}
