package br.com.fatec.main;

import br.com.fatec.commons.CorsFilter;
import br.com.fatec.controller.CompetenciesRoutes;
import br.com.fatec.controller.CourseRoutes;
import br.com.fatec.controller.EnrollsRoutes;
import br.com.fatec.controller.QuestionRoutes;
import br.com.fatec.controller.QuizRoutes;
import br.com.fatec.controller.UserRoutes;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;


public class Main {
	
	private static final String IP_ADDRESS = "localhost";
	private static final int PORT = 4567;
	
	public static void main(String[] args){
		
		port(PORT);
		
		staticFileLocation("/view");
		get( "/", (request, response) -> {
			response.redirect("/Login.html");
			return null;
		});
		
		CorsFilter.apply();
		
		startRest();

	}
	
	private static void startRest(){
		QuizRoutes.getQuiz();
		QuestionRoutes.getQuestions();
		CourseRoutes.getCourse();
		EnrollsRoutes.getEnrolls();
		CompetenciesRoutes.getCompetencies();
		UserRoutes.getUser();
	}

}