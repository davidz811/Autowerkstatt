package Model;

import Interfaces.CustomerInterface;
import Model.Repository.InMemoCars;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person implements CustomerInterface<Car> {
    private InMemoCars repo;
    private List<Car> ownedCars;


    public Customer(String firstName, String lastName, InMemoCars repo, List<Car> ownedCars) {
        super(firstName,lastName);
        this.repo = repo;
        this.ownedCars = ownedCars;
    }

    public List<Car> getRepo() {
        List<Car> cars = new ArrayList<>();
        for(Car c:this.repo.getCarList()) {
                cars.add(c);
        }
        return cars;
    }

    public List<Car> getOwnedCars() {
        return ownedCars;
    }

    @Override
    public void addCar(Car car){
        boolean found = false;
        for (Car c:this.repo.getCarList()){
            if(c.getId() == car.getId()) {
                found = true;
                throw new IllegalArgumentException("Already exists");
            }
        }
        if(!found){
            this.repo.addCar(car);
            this.ownedCars.add(car);
        }
    }
    @Override
    public void deleteCar(Car car){
        boolean deleted = false;
        for (Car c : this.repo.getCarList()) {
            if (c.getId() == car.getId()){
                this.repo.deleteCar(car);
                this.ownedCars.remove(car);
                deleted = true;
            }
        }
        if (!deleted)
            throw new IllegalArgumentException("Not found");
    }

    public void updateCar(Car car) {
        this.repo.updateCar(car);  //doesn't get used here
    }
}