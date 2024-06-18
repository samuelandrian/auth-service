package resource.samuelandrian.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import resource.samuelandrian.auth_service.entity.CredentialUsers;

@Repository
public interface CredentialUsersRepository extends JpaRepository<CredentialUsers, String> {
}
