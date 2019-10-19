package com.bw.movie.databean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Xuexiandong
 * data: 2019/10/18 15:15:26
 * function：电影 表
 */
@Entity
public class DataBeanMovie {
    @Id
    private Long id;
    //Banner
    private String jsonBanner;
    //正在热映
    private String jsonReleaseMovie;
    //即将上映
    private String jsonComingSoonMovie;
    //热门电影
    private String jsonHotMovie;

    @Generated(hash = 1437901817)
    public DataBeanMovie(Long id, String jsonBanner, String jsonReleaseMovie,
                         String jsonComingSoonMovie, String jsonHotMovie) {
        this.id = id;
        this.jsonBanner = jsonBanner;
        this.jsonReleaseMovie = jsonReleaseMovie;
        this.jsonComingSoonMovie = jsonComingSoonMovie;
        this.jsonHotMovie = jsonHotMovie;
    }

    @Generated(hash = 613486537)
    public DataBeanMovie() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJsonBanner() {
        return this.jsonBanner;
    }

    public void setJsonBanner(String jsonBanner) {
        this.jsonBanner = jsonBanner;
    }

    public String getJsonReleaseMovie() {
        return this.jsonReleaseMovie;
    }

    public void setJsonReleaseMovie(String jsonReleaseMovie) {
        this.jsonReleaseMovie = jsonReleaseMovie;
    }

    public String getJsonComingSoonMovie() {
        return this.jsonComingSoonMovie;
    }

    public void setJsonComingSoonMovie(String jsonComingSoonMovie) {
        this.jsonComingSoonMovie = jsonComingSoonMovie;
    }

    public String getJsonHotMovie() {
        return this.jsonHotMovie;
    }

    public void setJsonHotMovie(String jsonHotMovie) {
        this.jsonHotMovie = jsonHotMovie;
    }


}
