package mirea.morning.eventagencypr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import mirea.morning.eventagencypr.model.Order;
import mirea.morning.eventagencypr.model.Review;
import mirea.morning.eventagencypr.model.enums.Status;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {
    private Long id;
    private String name;
    private Long authorId;
    private String details;

    public static ReviewDto fromReview(Review review){
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setName(review.getName());
        dto.setDetails(review.getDetails());
        dto.setAuthorId(review.getAuthorId());

        return dto;
    }

    public Review toReview(){
        Review result = new Review();
        result.setId(id);
        result.setName(name);
        result.setDetails(details);
        result.setAuthorId(authorId);
        result.setCreated(new Date());
        result.setUpdated(new Date());
        result.setStatus(Status.ACTIVE);

        return result;
    }
}
