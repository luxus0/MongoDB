package spring_boot.spring_boot.MongoDB.MongoDB_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

@Autowired
private PlayerRepo repo;
private MongoTemplate mongoTemplate;



    @GetMapping("/getPlayer")
    public List<Player> getPlayerAll()
    {
        return repo.findAll();
    }

    @GetMapping("/getPlayer/{id}")
    public Player getPlayerById(@PathVariable("id") int id)
    {
        return repo.findById(id).get();
    }

    @PostMapping("/addPlayer")
    public void addPlayer(@RequestBody Player player)
    {
        repo.save(player);

    }

    @PostMapping("/insertPlayer")
    public void insertPlayer() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(1, "Wlasic", "Bielka", 23, 3, "Mikols Vetic"));
        playerList.add(new Player(2, "Bratik", "Mnemonia", 19, 5, "Diatel bortas"));


        mongoTemplate.insertAll(playerList);
    }

    @PutMapping("/updatePlayer")
    public void updatePlayer(@RequestBody Player player)
    {
       player.setId(player.getId());
       player.setName(player.getName());
       player.setSurname(player.getSurname());
       player.setAge(player.getAge());
       player.setNrPlayer(player.getNrPlayer());
       player.setTrainer(player.getTrainer());

       repo.save(player);

    }


}
