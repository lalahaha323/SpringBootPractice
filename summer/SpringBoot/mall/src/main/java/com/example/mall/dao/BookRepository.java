package com.example.mall.dao;

import com.example.mall.enty.Book;

/**
 * @author lala
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
}
