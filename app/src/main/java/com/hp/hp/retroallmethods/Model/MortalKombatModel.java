package com.hp.hp.retroallmethods.Model;

import java.util.List;

public class MortalKombatModel {
    /**
     * posts : [{"id":1,"title":"kabal","image":"https://i.ibb.co/7n2x9V9/images.jpg"},{"id":2,"title":"Scorpion","image":"https://i.ibb.co/wsM1wr9/0956089226fe3fad0a9da14dcf82ae43.jpg"},{"id":3,"title":"Raiden","image":"https://i.ibb.co/23c33Wf/217272.jpg"},{"id":4,"title":"Liu Kang","image":"https://i.ibb.co/FHyw95v/mortak-kombat-x-mkx-liu-kang-2036.jpg"},{"id":5,"title":"Kotal Kahn","image":"https://i.ibb.co/ZMdfN4F/da068s4-dbbb972d-8459-4bca-a4bd-8faedcf464af.png"},{"id":6,"title":"predator","image":"https://i.ibb.co/g68jFhB/predator-mortal-kombat-x-wallpaper-1024x768-wallpaper.jpg"},{"id":7,"title":"Sub-Zero","image":"https://i.ibb.co/MRwkq03/sub-zero-character-render.jpg"},{"id":8,"title":"Freddy Krugger","image":"https://i.ibb.co/XyM80fN/fredy.jpg"},{"id":9,"title":"Quan Chi","image":"https://i.ibb.co/PcvgkSc/quan.jpg"}]
     * profile : {"name":"MortalKombat"}
     * story : {"YOUTUBELINKS":"https://www.youtube.com/watch?v=qECXCLaHPgo","WEBLINKS":"https://www.denofgeek.com/us/games/mortal-kombat/248595/mortal-kombat-timeline-story-explained"}
     */

    private ProfileBean profile;
    private StoryBean story;
    private List<PostsBean> posts;

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public StoryBean getStory() {
        return story;
    }

    public void setStory(StoryBean story) {
        this.story = story;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class ProfileBean {
        /**
         * name : MortalKombat
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class StoryBean {
        /**
         * YOUTUBELINKS : https://www.youtube.com/watch?v=qECXCLaHPgo
         * WEBLINKS : https://www.denofgeek.com/us/games/mortal-kombat/248595/mortal-kombat-timeline-story-explained
         */

        private String YOUTUBELINKS;
        private String WEBLINKS;

        public String getYOUTUBELINKS() {
            return YOUTUBELINKS;
        }

        public void setYOUTUBELINKS(String YOUTUBELINKS) {
            this.YOUTUBELINKS = YOUTUBELINKS;
        }

        public String getWEBLINKS() {
            return WEBLINKS;
        }

        public void setWEBLINKS(String WEBLINKS) {
            this.WEBLINKS = WEBLINKS;
        }
    }

    public static class PostsBean {
        /**
         * id : 1
         * title : kabal
         * image : https://i.ibb.co/7n2x9V9/images.jpg
         */

        private int id;
        private String title;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
