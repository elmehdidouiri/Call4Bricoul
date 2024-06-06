package org.example.call4brikoul.Services;

import jakarta.transaction.Transactional;
import org.example.call4brikoul.DTO.ClientDTO;
import org.example.call4brikoul.Repository.AddressRepository;
import org.example.call4brikoul.Repository.ClientRepository;
import org.example.call4brikoul.Repository.RegionRepository;
import org.example.call4brikoul.Repository.VilleRepository;
import org.example.call4brikoul.models.Address;
import org.example.call4brikoul.models.Client;
import org.example.call4brikoul.models.Region;
import org.example.call4brikoul.models.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final VilleRepository villeRepository;
    private final RegionRepository regionRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository,
                         AddressRepository addressRepository,
                         VilleRepository villeRepository,
                         RegionRepository regionRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.villeRepository = villeRepository;
        this.regionRepository = regionRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public Client createClient(ClientDTO clientDTO) {

        Client client = new Client();
        client.setNom(clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());
        client.setTelephone(clientDTO.getTelephone());

        // 🏠 Adresse
        if (clientDTO.getAdresse() != null) {
            Address adresse = new Address();
            adresse.setRue(clientDTO.getAdresse().getRue());

            // ✅ Ville
            if (clientDTO.getAdresse().getVilleId() != null) {
                Ville ville = villeRepository.findById(clientDTO.getAdresse().getVilleId())
                        .orElseThrow(() -> new RuntimeException("Ville non trouvée"));
                adresse.setVille(ville);
            }

            // ✅ Région
            if (clientDTO.getAdresse().getRegionId() != null) {
                Region region = regionRepository.findById(clientDTO.getAdresse().getRegionId())
                        .orElseThrow(() -> new RuntimeException("Région non trouvée"));
                adresse.setRegion(region);
            }

            addressRepository.save(adresse);
            client.setAdresse(adresse);
        }

        return clientRepository.save(client);
    }
    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return null;
        }
        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setEmail(clientDetails.getEmail());
        client.setPassword(clientDetails.getPassword());
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
