param (
    [string]$filePath
)

if (-Not (Test-Path -Path $filePath)) {
    Write-Output "File not found: $filePath"
    exit
}

try {
    $bytes = Get-Content -Path $filePath -Encoding Byte -TotalCount 4
    $hexString = -join ($bytes | ForEach-Object { "{0:X2}" -f $_ })
    Write-Output "Magic number: $hexString"
} catch {
    Write-Output "An error occurred: $_"
}
