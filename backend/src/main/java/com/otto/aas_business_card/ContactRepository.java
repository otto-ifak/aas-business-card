package com.otto.aas_business_card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ContactRepository extends JpaRepository<Contact, UUID> {
}
