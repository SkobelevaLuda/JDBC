package dao;

import model.City;

import java.util.Optional;

public interface CityDao {
    Optional<City> findById(long id);
}
