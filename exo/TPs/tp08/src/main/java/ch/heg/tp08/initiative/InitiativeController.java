package ch.heg.tp08.initiative;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InitiativeController {
  private final String ENDPOINT = "/initiatives";
  private final InitiativeManager initiativeManager;

  @Autowired
  public InitiativeController(InitiativeManager initiativeManager) {
    this.initiativeManager = initiativeManager;
  }

  @GetMapping(ENDPOINT)
  public List<Initiative> getInitiatives() {
    return this.initiativeManager.findAll();
  }

  @GetMapping(ENDPOINT + "/{id}")
  public Initiative getInitiative(@PathVariable Long id) {
    return this.initiativeManager.find(id);
  }

  @PostMapping("/write/initiative")
  public ResponseEntity<Initiative> create(@RequestBody Initiative initiative) {
    this.initiativeManager.create(initiative);
    return ResponseEntity.ok(initiative);
  }

  @PutMapping("/write/initiative/{id}")
  public ResponseEntity<Initiative> update(
      @PathVariable Long id, @RequestBody Initiative initiative) {
    Initiative existingInitiative = this.initiativeManager.find(id);
    if (existingInitiative == null) {
      return ResponseEntity.notFound().build();
    }
    initiative.setId(id);
    Initiative updatedInitiative = this.initiativeManager.update(initiative);
    return ResponseEntity.ok(updatedInitiative);
  }

  @DeleteMapping("/write/initiative/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    boolean deleted = this.initiativeManager.delete(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
