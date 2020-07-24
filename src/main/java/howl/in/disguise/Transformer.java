package howl.in.disguise;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Transformer implements Comparable<Transformer> {
    @Id
	private Long id;

	private String name;

	@Column(columnDefinition = "VARCHAR(60) CHECK (allegiance IN('Autobot', 'Decepticon'))")
	private String allegiance;

    private Long strength;
    private Long intelligence;
    private Long speed;
    private Long endurance;
    private Long rank;
    private Long courage;
    private Long firepower;
    private Long skill;

	public Transformer() {
		super();
	}

	public Transformer(Long id, String name, String allegiance, Long strength, Long intelligence, Long speed, Long endurance, Long rank, Long courage, Long firepower, Long skill) {
		super();
		this.id = id;
		this.name = name;
		this.allegiance = allegiance;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
	}

    public Transformer(int i, String frodo, String autobot, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAllegiance() {
		return allegiance;
	}
	public void setAllegiance(String allegiance) {
		this.allegiance = allegiance;
	}

    public Long getStrength() {
        return strength;
    }
    public void setStrength(Long strength) {
        this.strength = strength;
    }

    public Long getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(Long intelligence) {
        this.intelligence = intelligence;
    }

    public Long getSpeed() {
        return speed;
    }
    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getEndurance() {
        return endurance;
    }
    public void setEndurance(Long endurance) {
        this.endurance = endurance;
    }

    public Long getRank() {
        return rank;
    }
    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getCourage() {
        return courage;
    }
    public void setCourage(Long courage) {
        this.courage = courage;
    }

    public Long getFirepower() {
        return firepower;
    }
    public void setFirepower(Long firepower) {
        this.firepower = firepower;
    }

    public Long getSkill() {
        return skill;
    }
    public void setSkill(Long skill) {
        this.skill = skill;
    }

    @JsonIgnore
    public Long getOverallPower(){
	    return this.strength + this.intelligence + this.speed + this.endurance + this.firepower;
    }

    @Override
    public int compareTo(Transformer transformer) {
        return (transformer.getRank() < this.getRank() ? -1 :
                (transformer.getRank().equals(this.getRank()) ? 0 : 1));
    }

}
