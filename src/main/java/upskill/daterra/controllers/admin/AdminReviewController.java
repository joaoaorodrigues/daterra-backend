package upskill.daterra.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.daterra.entities.Review;
import upskill.daterra.repositories.ReviewRepository;

import java.util.List;

@RestController
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/pendentes")
    public List<Review> getPendingReviews() {
        return reviewRepository.findByApproved(false);
    }

    @GetMapping("/aprovadas")
    public List<Review> getApprovedReviews() {
        return reviewRepository.findByApproved(true);
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<String> approveReview(@PathVariable Long id) {
        return reviewRepository.findById(id).map(review -> {
            review.setApproved(true);
            reviewRepository.save(review);
            return ResponseEntity.ok("Review aprovada com sucesso.");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/rejeitar/{id}")
    public ResponseEntity<String> rejectReview(@PathVariable Long id) {
        if (!reviewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reviewRepository.deleteById(id);
        return ResponseEntity.ok("Review rejeitada com sucesso.");
    }
}
