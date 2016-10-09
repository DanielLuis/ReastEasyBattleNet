package br.com.reasteasy.battlenet.vo;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.reasteasy.battlenet.support.Formatter;

public class WowCharacter
{
    private String totalHonorableKills;

    private String thumbnail;

    private String level;

    private String achievementPoints;

    private String battlegroup;

    private String lastModified;

    private String realm;

    private String name;

    private String faction;

    private String calcClass;

    private String gender;
    
    @JsonProperty(value ="class")
    private String clazz;

    private String race;

    public String getTotalHonorableKills ()
    {
        return totalHonorableKills;
    }

    public void setTotalHonorableKills (String totalHonorableKills)
    {
        this.totalHonorableKills = totalHonorableKills;
    }

    public String getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getLevel ()
    {
        return level;
    }

    public void setLevel (String level)
    {
        this.level = level;
    }

    public String getAchievementPoints ()
    {
        return achievementPoints;
    }

    public void setAchievementPoints (String achievementPoints)
    {
        this.achievementPoints = achievementPoints;
    }

    public String getBattlegroup ()
    {
        return battlegroup;
    }

    public void setBattlegroup (String battlegroup)
    {
        this.battlegroup = battlegroup;
    }

    public String getLastModified ()
    {
        return lastModified;
    }

    @JsonIgnore
    public String getLastModifiedFormatted ()
    {
    	if (StringUtils.isNotEmpty(lastModified)){
    			Calendar cal = Calendar.getInstance();
    		   cal.setTimeInMillis(Long.valueOf(lastModified));
    		
            return Formatter.getDateToString(cal.getTime());
    	}
    	
        return lastModified;
    }

    public void setLastModified (String lastModified)
    {
        this.lastModified = lastModified;
    }

    public String getRealm ()
    {
        return realm;
    }

    public void setRealm (String realm)
    {
        this.realm = realm;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getFaction ()
    {
        return faction;
    }

    public void setFaction (String faction)
    {
        this.faction = faction;
    }

    public String getCalcClass ()
    {
        return calcClass;
    }

    public void setCalcClass (String calcClass)
    {
        this.calcClass = calcClass;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    

    public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getRace ()
    {
        return race;
    }

    public void setRace (String race)
    {
        this.race = race;
    }

    @Override
    public String toString() {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return mapper.defaultPrettyPrintingWriter().writeValueAsString(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    	
    }
			
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(lastModified)
				.append(name)
				.append(realm)
				.append(battlegroup)
				.append(race)
				.append(gender)
				.append(clazz)
				.append(level)
				.append(achievementPoints)
				.append(thumbnail)
				.append(calcClass)
				.append(faction)
				.append(totalHonorableKills).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof WowCharacter) == false) {
			return false;
		}
		
		WowCharacter obj = ((WowCharacter) other);
		return new EqualsBuilder().append(lastModified, obj.lastModified)
								  .append(name, obj.name)
								  .append(realm, obj.realm)
								  .append(battlegroup, obj.battlegroup)
								  .append(race, obj.race)
								  .append(gender, obj.gender)
								  .append(clazz, obj.clazz)
								  .append(level, obj.level)
								  .append(achievementPoints, obj.achievementPoints)
								  .append(thumbnail, obj.thumbnail)
								  .append(calcClass, obj.calcClass)
								  .append(faction, obj.faction)
								  .append(totalHonorableKills, obj.totalHonorableKills)
								  .isEquals();
	}

}
