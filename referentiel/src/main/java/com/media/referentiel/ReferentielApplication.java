package com.media.referentiel;

import java.time.Duration;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.media.referentiel.model.Film;
import com.media.referentiel.model.Media;
import com.media.referentiel.model.Serie;
import com.media.referentiel.repository.MediaRepository;


@SpringBootApplication
public class ReferentielApplication {
	
	public static ArrayList<Media> maList = new ArrayList<>();
	static {
		String desc1 = "Avec l'identité de Spiderman désormais révélée, celui-ci est démasqué et n'est plus en mesure de séparer sa vie normale en tant que Peter Parker des enjeux élevés d'être un superhéros. Lorsque Peter demande de l'aide au docteur Strange, les enjeux deviennent encore plus dangereux, l'obligeant à découvrir ce que signifie vraiment être Spiderman.";
		Media pro1 = new Film("Spider Man: no way home", "Film","Action/Aventure",desc1,"FR", true,"https://www.youtube.com/watch?v=o-qvJ2iUqvA", 2*60 + 54, 2021);
		String desc2 = "Dans sa deuxième année de lutte contre le crime, le milliardaire et justicier masqué Batman explore la corruption qui sévit à Gotham et notamment comment elle pourrait être liée à sa propre famille, les Wayne, à qui il doit toute sa fortune. En parallèle, il enquête sur les meurtres d'un tueur en série qui se fait connaître sous le nom de Sphinx et sème des énigmes cruelles sur son passage.";
		Media pro2 = new Film("The Batman",  "Film","Action/Aventure", desc2,"FR",true,"https://www.youtube.com/watch?v=hWRSJlp50rQ", 2*60 + 39, 2022);
		String desc3 = "Le chasseur de trésors Victor Sully Sullivan recrute Nathan Drake pour l'aider à récupérer une fortune vieille de 500 ans amassée par l'explorateur Ferdinand Magellan.";
		Media pro3 = new Film("Uncharted", "Film","Action/Aventure",desc3,"FR", true,"https://www.youtube.com/watch?v=wg3cxfIiPmQ", 1*60 + 31, 2022 );
		String desc4 = "Ayant besoin d'argent pour couvrir les frais médicaux de sa femme, un vétéran fait équipe avec son frère adoptif pour voler 32 millions de dollars à une banque de Los Angeles. Cependant, lorsque leur vol tourne mal, ceux-ci détournent une ambulance qui transporte un policier gravement blessé et une ambulancière.";
		Media pro4 = new Film("Ambulance", "Film","Thriller", desc4, "FR",true,"https://www.youtube.com/watch?v=Hsawq4dFcRY", 2*60 + 49, 2022);
		String desc5 ="La nouvelle comédie d’aventures de Dreamworks Animations, inspirée par la série éponyme de livres pour enfants à succès, met en scène une bande d’animaux, redoutables criminels de haut vol, qui sont sur le point de commettre leur méfait le plus éclatant : devenir des citoyens respectables.";
		Media pro5 = new Film("Les Bad Guys", "Film","Comédie", desc5, "FR",true,"https://www.youtube.com/watch?v=lN9K4kdKNh4", 2*60 + 31, 2021);
		Media proV = new Film("Les Bad Guys", "Film","Comédie", "description", "US",false,"https://www.youtube.com/watch?v=lN9K4kdKNh4", 2*60 + 31, 2021);
		ArrayList<ArrayList<String>> saison6 = new ArrayList<ArrayList<String>>();
		ArrayList<String> list6 = new ArrayList<String>();
        list6.add("https://www.youtube.com/watch?v=C8W3rVuiHvg");
        list6.add("https://www.youtube.com/watch?v=yhSMlJeVebI");
        list6.add("https://www.youtube.com/watch?v=ASRUVq4XWH0");
        saison6.add(list6);
        saison6.add(list6);
        saison6.add(list6);
        String desc6 = "Dans les lotissements de l'est de Londres, le trafiquant de drogue Dushane est déterminé à devenir le Top Boy de la région et l'adolescent Ra'Nell est contraint de gagner en maturité suite à la rupture de sa mère.";
		Media pro6 = new Serie("Top Boy", "Serie","Drame", desc6,"FR",true,saison6, 1*60 + 41, 2020);
		ArrayList<ArrayList<String>> saison7 = new ArrayList<ArrayList<String>>();
		ArrayList<String> list7 = new ArrayList<String>();
        list7.add("https://www.youtube.com/watch?v=k_e1o3H5zuU");
        saison7.add(list7);
        String desc7 = "Tariq St. Patrick est tiraillé entre sa nouvelle vie et son désir de se libérer de l'héritage paternel tout en faisant face à la pression croissante de sauver sa famille, dont sa mère Tasha, accusée du meurtre commis par son fils."; 
		Media pro7 = new Serie("Power Book II: Ghost", "Serie", "Crime", desc7,"FR", true,saison7, 2*60 + 54, 2020);
		ArrayList<ArrayList<String>> saison8 = new ArrayList<ArrayList<String>>();
		ArrayList<String> list8 = new ArrayList<String>();
        list8.add("https://www.youtube.com/watch?v=xIBiJ_SzJTA");
        saison8.add(list8);
        String desc8 = "Elliot Alderson est un jeune informaticien vivant à New York, qui travaille en tant qu'ingénieur en sécurité informatique pour Allsafe Security. Celui-ci luttant constamment contre un trouble d'anxiété sociale et de dépression.";
		Media pro8 = new Serie("Mr. Robot", "Serie", "Drame", desc8, "FR",true,saison8, 2*60 + 24, 2015);
		maList.add(pro1);
		maList.add(pro2);
		maList.add(pro3);
		maList.add(pro4);
		maList.add(pro5);
		maList.add(pro6);
		maList.add(pro7);
		maList.add(pro8);
		maList.add(proV);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReferentielApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(MediaRepository mediaRepo) throws Exception {
		
        return args -> {
        	mediaRepo.saveAll(maList);
        };
    }
}
