package com.tcs.bookreview.service;

import com.tcs.bookreview.model.Book;
import org.springframework.stereotype.Service;
import static com.tcs.bookreview.service.ConstantsService.PAGE_SIZE;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements IPageService{

    @Override
    public List<Book> getBookForPage(List<Book> books, int page) {

        if(books==null){
            return new ArrayList<Book>();
        }

        if(books.isEmpty()){
            return books;
        }

        int startRecord = calculateStartRecord(page);
        int endRecord = calculateEndRecord(books, page);

        return books.subList(startRecord, endRecord);
    }

    @Override
    public int getTotalPages(int resultSize) {
        if(resultSize<PAGE_SIZE){
            return 1;
        }
        return resultSize%PAGE_SIZE == 0 ? resultSize/PAGE_SIZE : (resultSize/PAGE_SIZE) + 1;
    }

    private int calculateStartRecord(int page){
        if(page==0 || page==1){
            return 0;
        }

        return (page*PAGE_SIZE)-PAGE_SIZE;
    }

    private int calculateEndRecord(List<Book> books, int page){
        int returnValue = 0;
        if(page==0 || page==1){
            returnValue = PAGE_SIZE;
        }
        returnValue = page*PAGE_SIZE;

        if(books.size()>returnValue){
            return returnValue;
        } else {
            return books.size();
        }
    }

}
