import java.util.*;
import java.io.*;

public class User{
	//public String name;
	public String userID;
	public LinkedList listOfSubscriptions;
	public AVLTree postTree;
	public User(String str){
		this.userID = str;
		this.listOfSubscriptions = new LinkedList();
		this.postTree = new AVLTree();
	}
	public void subscribe(User d){
		this.listOfSubscriptions.insertRear(d);
		return;
	}
	public boolean unSubscribe(User d){ //returns false for error statement
		if(this.listOfSubscriptions.isMember(d) == false){
			//System.out.println("Error - user not subscribed to " + d.userID);
			return false;
		}else{
			this.listOfSubscriptions.deleteElement(d);
			return true;
		}
		/*Node curr = this.listOfSubscriptions.head;
		while(curr != null){
			if(curr)
		}*/
	}
	public Post findPost(String pid){
		Node curr = this.listOfSubscriptions.head;
		while(curr != null){
			if(curr.data.postTree.findPostWithID(pid) != null){
				return curr.data.postTree.findPostWithID(pid);
			}
			curr = curr.next;
		}
		return null;
	}

	public void readPosts(int time){
		Node curr = this.listOfSubscriptions.head;
		while(curr != null){
			curr.data.postTree.inorderPrint(curr.data.postTree.root,time);
			curr = curr.next;
		}
		return;
	}
	public boolean checkForPosts(){
		Node curr = this.listOfSubscriptions.head;
		while(curr != null){
			if(!(curr.data.postTree.isEmpty())){
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	public User getSubscribedUser(String pid){
		Node curr = this.listOfSubscriptions.head;
		while(curr != null){
			if(curr.data.userID.equals(pid)){
				return curr.data;
			}
			curr = curr.next;
		}
		return null;
	}
	/*public void publishPost(int time, String uid, String text, String textID){
		Post newPost = new Post(time,uid,text,textID);
		this.postTree.insert(newPost);
		return;
	}
	public void publishRepost(int time, String uid, String ptid, String text, String textID){
		Post newPost = new Post(time,uid,text,textID);
		Post targetPost = this.postTree.findPost(ptid);
		newPost.repostID = targetPost;
		this.postTree.insert(newPost);
		return;
	}
	public void publishReply(int time, String uid, String ptid, String text, String textID){
		Post newPost = new Post(time,uid,text,textID);
		Post targetPost = this.postTree.findPost(ptid);
		newPost.replyID = targetPost;
		this.postTree.insert(newPost);
		return;
	}
	public String performAction(String actionMessage){
		String actionWord = "";
		int i=0;
		while(actionMessage.charAt(i) != ','){
			actionWord = actionWord + actionMessage.substring(i,i+1);
			i++;
		}
		i = i+1;
		if(actionWord.equals("PUBLISH")){
			int j = i;
			while(actionMessage.charAt(i) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.parseInt(t);
			j = j+1;
			i = j;
			while(actionMessage.charAt(j)){
				j++;
			}
			String userid = actionMessage(i,j);
			j = j+1;
			i = j;
			if(actionMessage.charAt(i) == 'N'){
				while(actionMessage.charAt(j) != ','){
					j++;
				}
				j = j+1;
				i = j;
				while(actionMessage.char(j) != ','){
					j++;
				}
				String text = actionMessage.substring(i,j);
				j = j+1;
				i = j;
				String textid = actionMessage.substring(i);
				this.publishPost(time,userid,text,textid);
				System.out.println(actionMessage);
				return actionMessage;
			}else{
				if(actionMessage.charAt(j+3) == 'O'){
					while(actionMessage.charAt(j) != '('){
						j++;
					}
					j++;
					i = j;
					while(actionMessage.charAt(j) != ')'){
						j++;
					}
					String postid = actionMessage.substring(i,j);
					j = j+1;
					i = j;
					while(actionMessage.charAt(j) != ','){
						j++;
					}
					String text = actionMessage.substring(i,j);
					j = j+1;
					i = j;
					String textid = actionMessage.substring(i);
					this.publishRepost(time,userid,postid,text,textid);
					System.out.println(actionMessage);
					return actionMessage;
				}else{
					while(actionMessage.charAt(j) != '('){
					j++;
					}
					j++;
					i = j;
					while(actionMessage.charAt(j) != ')'){
						j++;
					}
					String postid = actionMessage.substring(i,j);
					j = j+1;
					i = j;
					while(actionMessage.charAt(j) != ','){
						j++;
					}
					String text = actionMessage.substring(i,j);
					j = j+1;
					i = j;
					String textid = actionMessage.substring(i);
					this.publishReply(time,userid,postid,text,textid);
					System.out.println(actionMessage);
					return actionMessage;
				}
			}
		}else if(actionWord.equals("SUBSCRIBE")){
			int j = i;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.toString(t);
			j = j+1;
			i = j;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String userid = actionMessage.substring(i,j);
			j = j+1;
			i = j;
			String publisherid = actionMessage.substring(i);
			this.subscribe 
		}else if(actionWord.equals("UNSUBSCRIBE")){
			int j = i;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.toString(t);
			j = j+1;
			i = j;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String userid = actionMessage.substring(i,j);
			j = j+1;
			i = j;
			String publisherid = actionMessage.substring(i);
			this.subscribe 
		}
	}*/
}