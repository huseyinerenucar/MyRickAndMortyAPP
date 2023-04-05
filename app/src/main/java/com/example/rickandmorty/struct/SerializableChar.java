package com.example.rickandmorty.struct;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.rickandmortyapi.Character;
import com.rickandmortyapi.Episode;
import com.rickandmortyapi.Location;

import java.net.URL;
import java.util.List;

public class SerializableChar implements Parcelable {

    private String name;
    private Character.Status status;
    private String species;
    private String type;
    private Character.Gender gender;
    private Location originLocation;
    private Location lastKnownLocation;
    private URL image;
    private List<Episode> episodes;

    public SerializableChar(String name, Character.Status status, String species, String type, Character.Gender gender, Location originLocation, Location lastKnownLocation, URL image, List<Episode> episodes) {
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.originLocation = originLocation;
        this.lastKnownLocation = lastKnownLocation;
        this.image = image;
        this.episodes = episodes;
    }

    protected SerializableChar(Parcel in) {
        name = in.readString();
        species = in.readString();
        type = in.readString();
    }

    public static final Creator<SerializableChar> CREATOR = new Creator<>() {
        @Override
        public SerializableChar createFromParcel(Parcel in) {
            return new SerializableChar(in);
        }

        @Override
        public SerializableChar[] newArray(int size) {
            return new SerializableChar[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character.Status getStatus() {
        return status;
    }

    public void setStatus(Character.Status status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Character.Gender getGender() {
        return gender;
    }

    public void setGender(Character.Gender gender) {
        this.gender = gender;
    }

    public Location getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(Location originLocation) {
        this.originLocation = originLocation;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(species);
        dest.writeString(type);
    }
}
