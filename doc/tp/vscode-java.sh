# vscode-java.sh
# chmod +x vscode-java.sh

export JAVA_VERSION=21
export JAVA_HOME="/usr/lib/jvm/java-$JAVA_VERSION-openjdk-amd64"
export PATH="${JAVA_HOME}/bin:${PATH}"

sudo apt-get update
sudo apt-get install ca-certificates-java libbz2-dev openjdk-"$JAVA_VERSION"-jdk openjdk-${JAVA_VERSION}-jre-headless -y --no-install-recommends
sudo apt-get install maven -y

java --version
mvn --version

code-server --install-extension adamraichu.zip-viewer
code-server --install-extension redhat.java
code-server --install-extension vscjava.vscode-java-pack
code-server --install-extension bierner.markdown-mermaid