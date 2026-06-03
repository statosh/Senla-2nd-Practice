package com.senprac.sto.car.service;

import com.senprac.sto.car.dto.CarDto;
import com.senprac.sto.car.mapper.CarMapper;
import com.senprac.sto.car.repository.CarRepository;
import com.senprac.sto.client.repository.ClientRepository;
import com.senprac.sto.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CarMapper carMapper;

    public CarDto addCar(CarDto carDto, Long clientId) {
        return clientRepository.findById(clientId).map(client -> {
            Car car = carMapper.toEntity(carDto);
            car.setClient(client);
            return carMapper.toDto(carRepository.save(car));
        }).orElseThrow(() -> new RuntimeException("Клиент не найден"));
    }

    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .toList();
    }

    public Optional<CarDto> getCarById(Long id) {
        return carRepository.findById(id).map(carMapper::toDto);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}