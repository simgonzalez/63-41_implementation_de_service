package ch.heg.cours6341.initiative;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Initiative {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String titre;
  private LocalDate created;
  private long noSignature = 0;

  @Enumerated(EnumType.STRING)
  private Status status;

  public Initiative(String titre, LocalDate created, long noSignature, Status status) {

    this.titre = titre;
    this.created = created;
    this.noSignature = noSignature;
    this.status = status;
  }

  public Initiative() {}

  public Initiative(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public Initiative setId(long id) {
    this.id = id;
    return this;
  }

  public String getTitre() {
    return titre;
  }

  public Initiative setTitre(String titre) {
    this.titre = titre;
    return this;
  }

  public LocalDate getCreated() {
    return created;
  }

  public Initiative setCreated(LocalDate created) {
    this.created = created;
    return this;
  }

  public long getNoSignature() {
    return noSignature;
  }

  public Initiative setNoSignature(long noSignature) {
    this.noSignature = noSignature;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public Initiative setStatus(Status status) {
    this.status = status;
    return this;
  }
}
