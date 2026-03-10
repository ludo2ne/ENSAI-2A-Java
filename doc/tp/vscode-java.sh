# vscode-java.sh
# chmod +x vscode-java.sh

JAVA_VERSION=21
sudo apt-get update -y
sudo apt-get install -y --no-install-recommends \
  ca-certificates-java \
  openjdk-${JAVA_VERSION}-jdk \
  maven

export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
export PATH="$JAVA_HOME/bin:$PATH"

java --version
mvn --version

# Java extensions install
code-server --install-extension vscjava.vscode-java-pack
code-server --install-extension vmware.vscode-boot-dev-pack
code-server --install-extension visualstudioexptteam.vscodeintellicode
code-server --install-extension sonarsource.sonarlint-vscode

# Python extensions uninstall
code-server --uninstall-extension ms-python.flake8
code-server --uninstall-extension charliermarsh.ruff
code-server --uninstall-extension ms-python.debugpy
code-server --uninstall-extension ms-python.python
code-server --uninstall-extension ms-python.vscode-python-envs
code-server --uninstall-extension ms-toolsai.jupyter
code-server --uninstall-extension ms-toolsai.jupyter-keymap
code-server --uninstall-extension ms-toolsai.jupyter-renderers
code-server --uninstall-extension ms-toolsai.vscode-jupyter-cell-tags
code-server --uninstall-extension ms-toolsai.vscode-jupyter-slideshow

# Add branch name in prompt
BASHRC="$HOME/.bashrc"
sed -i "/PS1='.*01;32m.*\\\\u@\\\\h/c\\
    PS1='\\\${debian_chroot:+(\\\$debian_chroot)}\\\[\\\033[01;32m\\\]\\\u@\\\h\\\[\\\033[00m\\\]:\\\[\\\033[01;34m\\\]\\\w\\\[\\\033[33m\\\]\\\$(__git_ps1 \" (%s)\")\\\[\\\033[00m\\\]\\\$ '" \
"$BASHRC"