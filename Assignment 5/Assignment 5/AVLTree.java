import java.util.*;
import java.io.*;

class AVLTreeNode{
	public Post data;
	public AVLTreeNode left;
	public AVLTreeNode right;
	public AVLTreeNode parent;
	public int height;
	public AVLTreeNode(Post p){
		this.data = p;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
	}
	public int getHeight(AVLTreeNode node){
		if(null == node){
			return -1;
		}else{ 
			return node.height;
		}
	}
	public void updateHeight(AVLTreeNode node){
		node.height = 1 + Math.max(node.getHeight(node.left),node.getHeight(node.right));
		return;
	}
	public boolean isExternal(){
		return (null == this.left && null == this.right);
	}
	public int heightBalance(){
		if(null == this){
			return 0;
		}else{
			return this.getHeight(this.right) - this.getHeight(this.left);
		}
	}
}

public class AVLTree{
	public AVLTreeNode root;
	public AVLTree(){
		this.root = null;
	}
	public boolean isEmpty(){
		return null == this.root;
	}
	public AVLTree leftSubTree(){
		AVLTree leftTree = new AVLTree();
		leftTree.root = this.root.left;
		return leftTree;
	}
	public AVLTree rightSubTree(){
		AVLTree rightTree = new AVLTree();
		rightTree.root = this.root.right;
		return rightTree;
	}
	public boolean isMember(Post p){
		if(null == this.root){
			return false;
		}else if(this.root.isExternal()){
			return (this.root.data.textID == p.textID); 
		}else{
			if(this.root.data.textID == p.textID){
				return true;
			}else{
				if(this.leftSubTree().isMember(p) || this.rightSubTree().isMember(p)){
					return true;
				}else{
					return false;
				}
			}
		}
	}
	public void updateHeightForEachNode(){
		if(null == this.root){
			return;
		}else{
			this.leftSubTree().updateHeightForEachNode();
			this.root.updateHeight(this.root);
			this.rightSubTree().updateHeightForEachNode();
			return;
		}
	}
		public AVLTreeNode detectLostInvariant(AVLTreeNode node){
		AVLTreeNode curr = node;
		if(null == curr){
			return curr;
		}
		while(curr != null){
			if(curr.heightBalance() == 2 || curr.heightBalance() == -2){
				return curr;
			}
			curr = curr.parent;
		}
		return curr;
	}
	public void leftLeftRotate(){
		AVLTreeNode p =this.root.parent;
		AVLTreeNode n1 = this.root;
		AVLTreeNode n2 = this.root.left;
		AVLTreeNode n3 = this.root.left.left;
		AVLTreeNode n4 = n2.right;
		if(p != null){
			if(p.left == n1){
				this.root = n2;
				n2.parent = p;
				p.left = n2;
			}else{
				this.root = n2;
				n2.parent = p;
				p.right = n2;
			}
		}else{
			this.root = n2;
			n2.parent = null;
		}
		n2.right = n1;
		n1.parent = n2;
		n1.left = n4;
		if(n4 != null){
			n4.parent = n1;
		}
		return;
	}
	public void leftRightRotate(){
		AVLTreeNode p = this.root.parent;
		AVLTreeNode n1 = this.root;
		AVLTreeNode n2 = this.root.left;
		AVLTreeNode n3 = this.root.left.right;
		AVLTreeNode n4 = n3.left;
		AVLTreeNode n5 = n3.right;
		if(p != null){
			if(p.left == n1){
				this.root = n3;
				n3.parent = p;
				p.left = n3;
			}else{
				this.root = n3;
				n3.parent = p;
				p.right = n3;
			}
		}else{
			this.root = n3;
			n3.parent = null;
		}
		n3.left = n1;
		n1.parent = n3;
		n3.right = n2;
		n2.parent = n3;
		n2.right = n4;
		if(n4 != null){
			n4.parent = n2;
		}
		n1.left = n5;
		if(null != n5){
			n5.parent = n1;
		}
		return;
	}
	public void rightLeftRotate(){
		AVLTreeNode p = this.root.parent;
		AVLTreeNode n1 = this.root;
		AVLTreeNode n2 = this.root.right;
		AVLTreeNode n3 = this.root.right.left;
		AVLTreeNode n4 = n3.left;
		AVLTreeNode n5 = n3.right;
		if(p != null){
			if(p.left == this.root){
				this.root = n3;
				n3.parent = p;
				p.left = n3;
			}else{
				this.root = n3;
				n2.parent = p;
				p.right = n3;
			}
		}else{
			this.root = n3;
			n3.parent = null;
		}
		n3.left = n1;
		n1.parent = n3;
		n3.right = n2;
		n2.parent = n3;
		n1.right = n4;
		if(n4 != null){
			n4.parent = n1;
		}
		n2.left = n5;
		if(n5 != null){
			n5.parent = n2;
		}
		return;
	}	
	public void rightRightRotate(){
		AVLTreeNode p = this.root.parent;
		AVLTreeNode n1 = this.root;
		AVLTreeNode n2 = this.root.right;
		AVLTreeNode n3 = this.root.right.right;
		AVLTreeNode n4 = n2.left;
		if(p != null){
			if(p.left == n1){
				this.root = n2;
				n2.parent = p;
				p.left = n2;
			}else{
				this.root = n2;
				n2.parent = p;
				p.right = n2;
			}
		}else{
				this.root = n2;
				n2.parent = null;
		}
		n2.left = n1;
		n1.parent = n2;
		n1.right = n4;
		if(n4 != null){
			n4.parent = n1;
		}
		return;
	}
	public void BSTInsert(Post p){
		boolean flag;
		if(root == null){
			this.root = new AVLTreeNode(p);
			return;
		}else{
			AVLTreeNode temp = root;
			AVLTreeNode curr;
			if(p.time > temp.data.time){
				flag = true;
				curr = temp.right;
			}else{
				flag = false;
				curr = temp.left;
			}
			while(curr != null){
				temp = curr;
				if(p.time > curr.data.time){
					flag = true;
					curr = curr.right;
				}else{
					flag = false;
					curr = curr.left;
				}
			}
			AVLTreeNode newNode = new AVLTreeNode(p);
			if(flag == true){
				temp.right = newNode;
			}else{
				temp.left = newNode;
			}
			return;
		}
	}
	public AVLTreeNode findNode(Post p){
		if(this.root == null){
			return null;
		}else{
			if(this.root.data.time == p.time){
				return this.root;
			}else if(this.root.data.time > p.time){
				return this.leftSubTree().findNode(p);
			}else{
				return this.rightSubTree().findNode(p);
			}
		}
	}
	public void insert(Post p){
			this.BSTInsert(p);
			this.updateHeightForEachNode();
			AVLTreeNode myNode = this.detectLostInvariant(findNode(p));
			AVLTree myTree = new AVLTree();
			myTree.root = myNode;
			if(myNode == null){
				return;
			}else{
				if(myNode.heightBalance() == -2){
					if(myNode.left.heightBalance() <= 0){
						myTree.leftLeftRotate();
						myTree.updateHeightForEachNode();
						return;
					}else{
						myTree.leftRightRotate();
						myTree.updateHeightForEachNode();
						return;
					}
				}else if(myNode.heightBalance() == 2){
					if(myNode.right.heightBalance() >=0){
						myTree.rightRightRotate();
						myTree.updateHeightForEachNode();
						return;
					}else{
						myTree.rightLeftRotate();
						myTree.updateHeightForEachNode();
						return;
					}
				}
			}
			this.Inorder(this.root);
			return;
	}
	public void Inorder(AVLTreeNode root){
		if(null == this.root){
			return;
		}else{
			this.Inorder(root.left);
			if(this.root.heightBalance() != 1 && this.root.heightBalance() != 0 && this.root.heightBalance() != 1){
				System.out.println("Mistake");
			}
			this.Inorder(root.right);
		}
		return;
	}
	public void inorderPrint(AVLTreeNode node, int time){
		if(null == node){
			return;
		}else{
			this.inorderPrint(node.left,time);
			node.data.printText(node.data,time);
			this.inorderPrint(node.right,time);
			return;
		}
	}
	public Post findPostWithID(String str){
		//finds a post with post id str
		if(null == this.root){
			return null;
		}else{
			if(this.leftSubTree().findPostWithID(str) != null){
				return this.leftSubTree().findPostWithID(str);
			}
			if(this.root.data.textID.equals(str)){
				return this.root.data;
			}
			if(this.rightSubTree().findPostWithID(str) != null){
				return this.rightSubTree().findPostWithID(str);
			}
		}
		return null;
	}
}