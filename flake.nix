{
  description = "Spring Boot backend geliştirme ortamı";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.05";

  outputs = { self, nixpkgs }:
  let
    system = "x86_64-linux";
    pkgs = nixpkgs.legacyPackages.${system};
  in {
    devShells.${system}.default = pkgs.mkShell {
      packages = [
        pkgs.jdk21
        pkgs.maven
        pkgs.postgresql
        pkgs.git
      ];

      shellHook = ''
        echo "spring boot backend geliştirme ortamı hazır."
        java -version
        mvn -version
      '';
    };
  };
}
