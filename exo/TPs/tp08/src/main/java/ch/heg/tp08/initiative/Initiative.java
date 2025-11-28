package ch.heg.tp08.initiative;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Initiative {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version private Long version;

  @Column(unique = true, nullable = false)
  @Nonnull
  private String titre;

  @Column(length = 2000)
  private String description;

  @Column(nullable = false)
  @Nonnull
  private LocalDate dateDepot;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Nonnull
  private Status statut;

  public Initiative() {}

  public Initiative(String titre, String description, LocalDate dateDepot, Status statut) {
    this.titre = titre;
    this.description = description;
    this.dateDepot = dateDepot;
    this.statut = statut;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDateDepot() {
    return dateDepot;
  }

  public void setDateDepot(LocalDate dateDepot) {
    this.dateDepot = dateDepot;
  }

  public Status getStatut() {
    return statut;
  }

  public void setStatut(Status statut) {
    this.statut = statut;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Initiative that)) return false;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
