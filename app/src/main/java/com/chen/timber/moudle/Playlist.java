
package com.chen.timber.moudle;

/**
 * Created by chen on 2016/4/13.
 */
public class Playlist {

    public final long id;
    public final String name;
    public final int songCount;

    public Playlist() {
        this.id = -1;
        this.name = "";
        this.songCount = -1;
    }

    public Playlist(long _id, String _name, int _songCount) {
        this.id = _id;
        this.name = _name;
        this.songCount = _songCount;
    }
}
