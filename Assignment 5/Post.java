import java.util.*;
import java.io.*;

public class Post{
	public int time;
	public String text;
	public String textID;
	public User userID;
	public Post repostID; //if post is not a repost this is null
	public Post replyID; // if post is not a reply this is null
	public Post(int t, User uid, String text, String tid){
		//declares new post. repost/reply pointers to be set manually
		this.time = t;
		this.userID = uid;
		this.textID = tid;
		this.text = text;
		this.repostID = null;
		this.replyID = null;
	}
	public void printText(Post myPost, int t){
		if(myPost.time <=t){
			if(myPost.repostID != null){
				System.out.print(myPost.repostID.text + " ");
			}else{
				System.out.print(myPost.text + " ");
			}
			return;
		}else{
			return;
		}
	}
}