package spring_boot.spring_boot.MongoDB.MongoDB_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoOperations;


public class PlayerService {

    @Autowired
    private PlayerRepo repo;

    @EventListener(ApplicationReadyEvent.class)
    public void saveDB() {
        Player player = new Player(1, "Wlasic", "Bielka", 23, 3, "Mikols Vetic");
        Player player2 = new Player(2, "Bratik", "Mnemonia", 19, 5, "Diatel bortas");

        repo.save(player);
        repo.save(player2);
    }

}
