package com.tcs.bookreview.dao;

import com.tcs.bookreview.model.BookReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewDao extends CrudRepository<BookReview, Integer> {
}
