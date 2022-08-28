package RestAPI;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAPIs {

	static int userID = 1;
	static int postID = 1;

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		userID = (int) Math.floor(Math.random() * (10 - 1 + 1) + 1);
		postID = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);
	}

	@Test
	public void print_comments_of_PostID() {
		Response response = given().when().get("comments?postId=" + postID).then().extract().response();
		
		List<String> commentsOnPost = response.jsonPath().getList("body");
		System.out.println("Comments of Post_ID: " + postID);
		for (String comment : commentsOnPost) {
			System.out.println(comment);
		}
	}
	
	@Test
	public static void verify_Album_Title_ByUserID() {
		Response response = given().when().get("albums?userId=" + userID).then().extract().response();

		List<String> userAlbumTitles = response.jsonPath().getList("title");
		for (String albumTitle : userAlbumTitles) {
			assertTrue(albumTitle.length() <= 300);
		}
	}
	
	@Test
	public void make_Post_With_UserID() {
		Response response = given().header("Content-type", "application/json").queryParam("userId", userID)
				.queryParam("title", "test API").queryParam("body", "Post API task for VOIS").when().post("/posts")
				.then().extract().response();
		assertEquals(response.statusCode(), 201);
		assertEquals(response.jsonPath().getString("id"), "101");
	}
	
	@Test
	public void print_Completed_TODO_Titles_ByUserID() {
		Response response = given().when().get("todos?userId=" + userID + "&completed=false").then().extract().response();

		List<String> userFalseTODOs = response.jsonPath().getList("title");
		System.out.println("False TODOs of UserID: " + userID);
		for (String TODO_Title : userFalseTODOs) {
			System.out.println(TODO_Title);
		}
	}

}