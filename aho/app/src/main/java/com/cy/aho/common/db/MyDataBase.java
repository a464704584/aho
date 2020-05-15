package com.cy.aho.common.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;

import com.cy.aho.bean.Knowledge;
import com.cy.aho.dao.KnowledgeDao;

/**
 * @创建者 CY
 * @创建时间 2020/5/14 11:09
 * @描述
 */

@Database(entities ={Knowledge.class} ,version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    public abstract KnowledgeDao getKnowledgeDao();
}
