package dev.moutamid.recyclerviewadapterproject;

public class MovieModel {
    String movieName, directorName, videoUrl, directorWikiUrl, videoWikiUrl;
    int imageId;

    public MovieModel(String movieName, String directorName, String videoUrl, String directorWikiUrl, String videoWikiUrl, int imageId) {
        this.movieName = movieName;
        this.directorName = directorName;
        this.videoUrl = videoUrl;
        this.directorWikiUrl = directorWikiUrl;
        this.videoWikiUrl = videoWikiUrl;
        this.imageId = imageId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDirectorWikiUrl() {
        return directorWikiUrl;
    }

    public void setDirectorWikiUrl(String directorWikiUrl) {
        this.directorWikiUrl = directorWikiUrl;
    }

    public String getVideoWikiUrl() {
        return videoWikiUrl;
    }

    public void setVideoWikiUrl(String videoWikiUrl) {
        this.videoWikiUrl = videoWikiUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public MovieModel() {
    }
}
