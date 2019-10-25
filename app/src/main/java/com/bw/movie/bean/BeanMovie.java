package com.bw.movie.bean;

/**
 * author: Xuexiandong
 * data: 2019/10/19 15:15:19
 * function：选择的电影   影院
 */
public class BeanMovie {
    private int movieId;
    private int cinemaId;
    private String movieName;
    private String videoPath;
    private String videoPathImg;
    private int nearby;
    private int Numberofpages;

    public BeanMovie(int movieId, int cinemaId, String movieName, String videoPath, String videoPathImg, int nearby, int numberofpages) {
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.movieName = movieName;
        this.videoPath = videoPath;
        this.videoPathImg = videoPathImg;
        this.nearby = nearby;
        Numberofpages = numberofpages;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoPathImg() {
        return videoPathImg;
    }

    public void setVideoPathImg(String videoPathImg) {
        this.videoPathImg = videoPathImg;
    }

    public int getNearby() {
        return nearby;
    }

    public void setNearby(int nearby) {
        this.nearby = nearby;
    }

    public int getNumberofpages() {
        return Numberofpages;
    }

    public void setNumberofpages(int numberofpages) {
        Numberofpages = numberofpages;
    }
}
