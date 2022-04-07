package com.catalogue.poster;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.catalogue.poster.model.Poster;
import com.catalogue.poster.repository.PosterRepository;

@SpringBootApplication
public class PosterApplication {
	
	public static ArrayList<Poster> maList = new ArrayList<>();
	static {
		Poster pro1 = new Poster("Spider-man: no way home", "https://d1fmx1rbmqrxrr.cloudfront.net/cnet/i/edit/2021/11/spider-man-annonce.jpg", "https://image.over-blog.com/CTzM3GtwJopHQX6FoZU5lI7Gsoo=/filters:no_upscale()/image%2F0995735%2F20211203%2Fob_afa363_untitled-1-41.jpg", true, (long) 1);
		Poster pro2 = new Poster("The batman","https://planet.s3.us-east-1.wasabisys.com/2022/03/the-new-poster-of-the-batman-for-china-gives-more-1024x576.jpg", "https://ewwnews.com/img/view-the-new-poster-for-the-china-premiere-of-the-batman.jpeg",true, (long) 2);
		Poster pro3 = new Poster("Uncharted","https://fr.web.img6.acsta.net/r_1024_576/pictures/22/02/03/10/10/0278057.jpg", "https://aniportalimages.s3.amazonaws.com/media/details/FGegewiguyewvftgewgA0znC20220211217112533.jpg", true,(long) 3);
		Poster pro4 = new Poster("Ambulance","https://fr.web.img5.acsta.net/r_1280_720/pictures/22/03/08/09/48/0055808.jpg", "https://dx35vtwkllhj9.cloudfront.net/universalstudios/ambulance/images/regions/fr/onesheet.jpg", true,(long) 4);
		Poster pro5 = new Poster("Les Bad Guys","https://www.lesuricate.org/wp-content/uploads/2022/03/les-bad-guys-poster.jpg", "https://static1.tribute.ca/poster/660x980/the-bad-guys-159192.jpg", true,(long) 5);
		Poster pro6 = new Poster("Top Boy","https://fr.web.img6.acsta.net/r_1280_720/pictures/19/09/16/15/16/3931893.jpg", "https://img01.products.bt.co.uk/content/dam/bt/portal/images/articles/tv/tv-drama-top-boy-2-netflix-hero.jpg/jcr:content/renditions/landscape-desktop.764.430.jpg", true,(long) 6);
		Poster pro7 = new Poster("Power Book II: Ghost","https://fr.web.img6.acsta.net/r_1280_720/pictures/20/08/06/16/22/1639486.jpg", "https://static.wikia.nocookie.net/starzpower/images/4/4e/PowerBookIIGhostS2Poster.jpg", true,(long) 7);
		Poster pro8 = new Poster("Mr. Robot","https://pbs.twimg.com/media/E9lbbI7WYAQTJ8l?format=jpg&name=small", "https://rukminim1.flixcart.com/image/416/416/jr58l8w0/poster/h/5/h/medium-mr-robot-poster-for-room-office-13-inch-x-19-inch-rolled-original-imafdy8fby9m5gbw.jpeg",true, (long) 8);
		maList.add(pro1);
		maList.add(pro2);
		maList.add(pro3);
		maList.add(pro4);
		maList.add(pro5);
		maList.add(pro6);
		maList.add(pro7);
		maList.add(pro8);
	}
	public static void main(String[] args) {
		SpringApplication.run(PosterApplication.class, args);
	}

	@Bean
    public CommandLineRunner run(PosterRepository posterRepo) throws Exception {
		
        return args -> {
        	System.out.println("runnnnninnnnnng");
        	posterRepo.deleteAll();
        	posterRepo.saveAll(maList);
        	System.out.println("sucesssssss running creattedd");

        };
    }
}
