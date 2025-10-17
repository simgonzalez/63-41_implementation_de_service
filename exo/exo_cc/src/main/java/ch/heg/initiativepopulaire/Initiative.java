package ch.heg.initiativepopulaire;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Initiative {
  public Initiative(
          String titre, String description, LocalDate date, Status etat_avancement) {
    this.titre = titre;
    this.description = description;
    this.date = date;
    this.etat_avancement = etat_avancement;
  }

  public int id() {
    return id;
  }

  public String titre() {
    return titre;
  }

  public String description() {
    return description;
  }

  public LocalDate date() {
    return date;
  }

  public Status etat_avancement() {
    return etat_avancement;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Initiative{");
    sb.append("id=").append(id);
    sb.append(", titre='").append(titre).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", date=").append(date);
    sb.append(", etat_avancement=").append(etat_avancement);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Initiative that = (Initiative) o;
    return Objects.equals(titre, that.titre) && Objects.equals(date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titre, date);
  }

  public Initiative() {}

  public Initiative setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String titre;

  private String description;

  @Column(nullable = false)
  private LocalDate date = LocalDate.now();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status etat_avancement;
}
