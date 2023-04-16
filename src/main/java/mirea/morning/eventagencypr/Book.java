package mirea.morning.eventagencypr;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity()
@Table(name = "Books", schema = "mirea")
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer miId;

    @Column(columnDefinition = "varchar(200) not null", insertable = true, length = 200, name = "BookName", nullable = false, unique = false, updatable = true)
    private String msBookName;

    @Column(columnDefinition = "varchar(100) not null", insertable = true, length = 100, name="Author", nullable = false, unique = false, updatable = true)
    private String msAuthor;

    public String getAuthor()
    {
        if(msAuthor == null)
            msAuthor = "";

        return(msAuthor);
    }

    public String getBookName()
    {
        if(msBookName == null)
            msBookName = "";

        return(msBookName);
    }

    public int getId()
    {
        if(miId == null)
            miId = 0;

        return(miId);
    }

    public void setAuthor(final String sAuthor)
    {
        if(sAuthor == null)
            msAuthor = "";
        else
            msAuthor = sAuthor;
    }

    public void setBookName(final String sBookName)
    {
        if(sBookName == null)
            msBookName = "";
        else
            msBookName = sBookName;
    }

    public void setId(final Integer iId)
    {
        if(iId == null)
            miId = 0;
        else
            miId = iId;
    }

}