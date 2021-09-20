import java.util.*;
import java.io.*;

public class SocialNetwork{
	public LinkedList dataBase;
	public SocialNetwork(){
		this.dataBase = new LinkedList();
	}
	public Post findPost(String pid){
		Node curr = this.dataBase.head;
		while(curr != null){
			if(curr.data.postTree.findPostWithID(pid) != null){
				return curr.data.postTree.findPostWithID(pid);
			}
			curr = curr.next;
		}
		return null;
	}
	public User getUser(String uid){
		Node curr = this.dataBase.head;
		while(curr != null){
			if(curr.data.userID.equals(uid)){
				return curr.data;
			}
			curr = curr.next;
		}
		User newUser = new User(uid);
		this.dataBase.insertRear(newUser);
		return newUser;
	}
	public boolean publishPost(int time, String uid, String text, String textID){
		User publisher = this.getUser(uid);
		if(findPost(textID) != null){
			System.out.println("Can't publish with tid " + textID);
			return false;
		}
		Post newPost = new Post(time,publisher,text,textID);
		publisher.postTree.insert(newPost);
		return true;
	}
	public boolean publishRepost(int time, String uid, String ptid, String text, String textID){
		User publisher = this.getUser(uid);
		if(findPost(textID) != null){
			System.out.println("Can't publish with tid " + textID);
			return false;
		}
		Post newPost = new Post(time,publisher,text,textID);
		Post targetPost = this.findPost(ptid);
		if(targetPost == null){
			System.out.println("No text with ptid " + textID);
			return false;
		}
		newPost.repostID = targetPost;
		publisher.postTree.insert(newPost);
		return true;
	}
	public boolean publishReply(int time, String uid, String ptid, String text, String textID){
		User publisher = this.getUser(uid);
		if(findPost(textID) != null){
			System.out.println("Can't publish with tid " + textID);
			return false;
		}
		Post newPost = new Post(time,publisher,text,textID);
		Post targetPost = this.findPost(ptid);
		if(targetPost == null){
			System.out.println("No text with ptid " + textID);
			return false;
		}
		newPost.replyID = targetPost;
		publisher.postTree.insert(newPost);
		return true;
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
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.parseInt(t);
			j = j+1;
			i = j;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String userid = actionMessage.substring(i,j);
			j = j+1;
			i = j;
			if(actionMessage.charAt(i) == 'N'){
				while(actionMessage.charAt(j) != ','){
					j++;
				}
				j = j+1;
				i = j;
				while(actionMessage.charAt(j) != ','){
					j++;
				}
				String text = actionMessage.substring(i,j);
				j = j+1;
				i = j;
				String textid = actionMessage.substring(i);
				//System.out.print(time + " " + userid + " " + text + " " + textid + "\n");
				if(this.publishPost(time,userid,text,textid)){
					System.out.println(actionMessage);
					return actionMessage;
				}else{
					return "";
				}
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
					j = j+2;
					i = j;
				//	while(actionMessage.charAt(j) != ','){
				//		j++;
				//	}
				//	String text = actionMessage.substring(i,j);
				//	j = j+1;
				//	i = j;
					String text = "";
					String textid = actionMessage.substring(i);
					if(this.publishRepost(time,userid,postid,text,textid)){
						System.out.println(actionMessage);
						return actionMessage;
					}else{
						return "";
					}
				}else{
					while(actionMessage.charAt(j) != '('){
					j++;
					}
					j = j+1;
					i = j;
					while(actionMessage.charAt(j) != ')'){
						j++;
					}
					String postid = actionMessage.substring(i,j);
					j = j+2;
					i = j;
					while(actionMessage.charAt(j) != ','){
						j++;
					}
					String text = actionMessage.substring(i,j);
					j = j+1;
					i = j;
					String textid = actionMessage.substring(i);
					if(this.publishReply(time,userid,postid,text,textid)){
						System.out.println(actionMessage);
						return actionMessage;
					}else{
						return "";	
					}
				}
			}
		}else if(actionWord.equals("SUBSCRIBE")){
			int j = i;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.parseInt(t);
			j = j+1;
			i = j;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String userid = actionMessage.substring(i,j);
			j = j+1;
			i = j;
			String publisherid = actionMessage.substring(i);
			//System.out.println(time + " " + userid + " " + publisherid);
			User myUser = this.getUser(userid);
			User targetUser = this.getUser(publisherid);
	//		targetUser.postTree = targetUser.postTree.findTreeWithTime(time);
			myUser.subscribe(targetUser);
			if(myUser.listOfSubscriptions.isMember(targetUser) == false){
				System.out.println("not present");
			}
			System.out.println(actionMessage);
			return actionMessage;
		}else if(actionWord.equals("UNSUBSCRIBE")){
			int j = i;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.parseInt(t);
			j = j+1;
			i = j;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String userid = actionMessage.substring(i,j);
			j = j+1;
			i = j;
			String publisherid = actionMessage.substring(i);
			User myUser = this.getUser(userid);
			User targetUser = myUser.getSubscribedUser(publisherid);
			//System.out.println(time + " " + userid + " " + publisherid);
			if(myUser.listOfSubscriptions.isMember(targetUser) == false){
				System.out.println("not present");
			}
			if(myUser.unSubscribe(targetUser)){
				System.out.println(actionMessage);
				return actionMessage;
			}else{
				System.out.println("Error - user of uid " + userid + " not subscribed to user of id " + publisherid);
				return "";
			}
		}else{
			int j = i;
			while(actionMessage.charAt(j) != ','){
				j++;
			}
			String t = actionMessage.substring(i,j);
			int time = Integer.parseInt(t);
			j = j+1;
			i = j;
			String userid = actionMessage.substring(i);
			User myUser = this.getUser(userid);
			/*if(myUser.postTree.root == null){
				System.out.println("No text available to uid " + userid);
				return actionMessage;
			}*/
			if(!myUser.checkForPosts()){
				System.out.println("No text available to uid " + userid);
				return "";
			}
			System.out.print(actionMessage + ", [");
			//myUser.postTree.inorderPrint(myUser.postTree.root);
			myUser.readPosts(time);
		/*	Node curr = myUser.listOfSubscriptions.head;
			while(curr != null){
				curr.data.postTree.root = curr.data.postTree.findTreeWithTime(time);
				curr = curr.next;
			}*/
			System.out.println("]");
			return actionMessage;
		}
	}
		public static void main ( String args [])
	{
		BufferedReader br = null;
		SocialNetwork myNetwork = new SocialNetwork();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions1.txt"));

			while ((actionString = br.readLine()) != null) {
				myNetwork.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}

/*class checker{
		public static void main ( String args [])
	{
		BufferedReader br = null;
		SocialNetwork myNetwork = new SocialNetwork();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions1.txt"));

			while ((actionString = br.readLine()) != null) {
				myNetwork.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}*/
