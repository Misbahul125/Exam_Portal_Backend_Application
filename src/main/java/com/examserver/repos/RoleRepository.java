package com.examserver.repos;

import com.examserver.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel , Long> {
}
