package org.example.call4brikoul.Services;

import org.example.call4brikoul.Repository.AddressRepository;
import org.example.call4brikoul.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) {
        if (address.getId_adr() != null && addressRepository.existsById(address.getId_adr())) {
            throw new IllegalArgumentException("Une adresse avec cet ID existe déjà dans la base de données.");
        }
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address addressDetails) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucune adresse trouvée avec l'ID spécifié."));

        existingAddress.setRue(addressDetails.getRue());
        existingAddress.setRegion(addressDetails.getRegion());
        existingAddress.setVille(addressDetails.getVille());

        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(Long id) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucune adresse trouvée avec l'ID spécifié."));

        addressRepository.delete(existingAddress);
    }
}
