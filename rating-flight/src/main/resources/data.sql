-- Insertion des vols
INSERT INTO flight (flight_number, company, flight_date) VALUES
('AF123', 'Air France',  '2024-06-01'),
('LH456', 'Lufthansa',   '2024-06-02'),
('BA789', 'British Airways', '2024-06-03');

-- Insertion des réponses aux avis
INSERT INTO rate_response (response, response_at) VALUES
('Merci pour votre retour !', '2024-06-05T10:00:00'),
('Nous sommes désolés pour ce désagrément.', '2024-06-06T12:00:00');

-- Insertion des avis
INSERT INTO rate (rating, comment, submitted_at, flight_id, rate_response_id) VALUES
(5, 'Excellent vol, personnel très agréable.', '2024-06-01T15:00:00', 1, 1),
(2, 'Retard important, peu d''informations.',   '2024-06-02T18:00:00', 2, 2),
(4, 'Vol confortable, mais repas moyen.',       '2024-06-03T09:00:00', 3, NULL);
