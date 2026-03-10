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

code-server --install-extension vscjava.vscode-java-pack
code-server --install-extension vmware.vscode-boot-dev-pack
code-server --install-extension visualstudioexptteam.vscodeintellicode
code-server --install-extension sonarsource.sonarlint-vscode

code-server --uninstall-extension ms-python.flake8

# Add branch name in prompt
BASHRC="$HOME/.bashrc"
sed -i "/PS1='.*01;32m.*\\\\u@\\\\h/c\\
    PS1='\\\${debian_chroot:+(\\\$debian_chroot)}\\\[\\\033[01;32m\\\]\\\u@\\\h\\\[\\\033[00m\\\]:\\\[\\\033[01;34m\\\]\\\w\\\[\\\033[33m\\\]\\\$(__git_ps1 \" (%s)\")\\\[\\\033[00m\\\]\\\$ '" \
"$BASHRC"