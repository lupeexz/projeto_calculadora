param([ValidateSet('testar','executar','documentar')][string]$Acao = 'testar')
$ErrorActionPreference = 'Stop'
Set-Location $PSScriptRoot
$javaBin = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME 'bin' } else { '' }
function Jdk([string]$nome) {
    if ($javaBin) { return Join-Path $javaBin "$nome.exe" }
    return $nome
}
function ConferirSaida {
    if ($LASTEXITCODE -ne 0) { throw "Comando Java falhou: $LASTEXITCODE" }
}
New-Item -ItemType Directory -Force build/classes,build/test-classes,lib | Out-Null
$junit = 'lib/junit-platform-console-standalone-1.11.4.jar'
if (!(Test-Path $junit)) {
    Invoke-WebRequest 'https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar' -OutFile $junit
}
& (Jdk 'javac') -encoding UTF-8 -d build/classes src/calculadora/Calculadora.java src/calculadora/Aplicacao.java
ConferirSaida
& (Jdk 'javac') -encoding UTF-8 -cp "build/classes;$junit" -d build/test-classes test/calculadora/TesteCalculadora.java
ConferirSaida
switch ($Acao) {
    'testar' {
        & (Jdk 'java') '-Dstdout.encoding=UTF-8' -jar $junit execute --class-path 'build/classes;build/test-classes' --scan-class-path --disable-ansi-colors
    }
    'executar' { & (Jdk 'java') '-Dstdout.encoding=UTF-8' -cp build/classes calculadora.Aplicacao }
    'documentar' {
        & (Jdk 'javadoc') -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -private -author -version -Xdoclint:all -classpath $junit -d (Join-Path $PSScriptRoot 'docs') src/calculadora/Calculadora.java src/calculadora/Aplicacao.java test/calculadora/TesteCalculadora.java
    }
}
ConferirSaida
