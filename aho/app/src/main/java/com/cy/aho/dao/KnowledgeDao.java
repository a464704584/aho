package com.cy.aho.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cy.aho.bean.Knowledge;

import java.util.List;

/**
 * @创建者 CY
 * @创建时间 2020/5/14 11:11
 * @描述
 */
@Dao
public interface KnowledgeDao {

    @Insert
    void insertAll(List<Knowledge> data);

    @Query("select * from knowledge")
    List<Knowledge> get();

    @Query("delete from knowledge")
    void delAll();



}
