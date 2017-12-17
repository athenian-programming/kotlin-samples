local-reset: local-clean local-migrate

local-clean:
	flyway -url=jdbc:postgresql://localhost:5432/postgres clean

local-migrate:
	flyway -url=jdbc:postgresql://localhost:5432/postgres migrate

