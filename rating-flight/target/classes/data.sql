
-- Insertion des avis
INSERT INTO rate (rating, comment, flight_number, company, flight_date, submitted_at, status) VALUES
(5, 'Excellent vol, personnel très attentionné', 'AF1234', 'Air France', '2025-07-06', '2025-07-06 10:30:00', 'PUBLISHED'),
(4, 'Bon vol mais repas moyen', 'AF5678', 'Air France', '2025-07-07', '2025-07-07 14:15:00', 'PROCESSED'),
(3, 'Service correct mais retard au décollage', 'LH9012', 'Lufthansa', '2025-07-06', '2025-07-06 18:45:00', 'PENDING'),
(5, 'Très satisfait du service', 'BA3456', 'British Airways', '2025-07-08', '2025-07-08 09:20:00', 'PUBLISHED'),
(2, 'Problèmes avec les bagages', 'EK7890', 'Emirates', '2025-07-09', '2025-07-09 22:10:00', 'REJECTED');

-- Insertion des réponses aux avis
INSERT INTO rate_response (response, response_at, rate_id) VALUES
('Merci pour votre retour positif !', '2025-07-06 11:30:00', 1),
('Nous sommes désolés pour la qualité du repas, nous allons faire remonter votre remarque.', '2025-07-07 16:00:00', 2),
('Nous nous excusons pour le retard. Une compensation vous sera proposée.', '2025-07-06 20:00:00', 3),
('Nous sommes ravis que vous ayez apprécié votre voyage !', '2025-07-08 10:15:00', 4),
('Nous sommes désolés pour ce désagrément. Notre service client va vous contacter.', '2025-07-10 09:00:00', 5);

-- Ajout de réponses supplémentaires pour montrer les discussions
INSERT INTO rate_response (response, response_at, rate_id) VALUES
('Pouvez-vous nous donner plus de détails sur le problème des bagages ?', '2025-07-10 10:30:00', 5),
('Merci encore pour votre fidélité !', '2025-07-06 14:00:00', 1);

