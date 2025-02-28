package repository;

import data.bcheck.BCheck;
import settings.repository.RepositorySettingsReader;

import java.util.List;

public class RepositoryFacade implements Repository<BCheck> {
    private final FileSystemRepository<BCheck> fileSystemRepository;
    private final GitHubRepository<BCheck> gitHubBCheckRepository;
    private final RepositorySettingsReader repositorySettingsReader;

    public RepositoryFacade(
            RepositorySettingsReader repositorySettingsReader,
            GitHubRepository<BCheck> gitHubBCheckRepository,
            FileSystemRepository<BCheck> fileSystemRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.gitHubBCheckRepository = gitHubBCheckRepository;
        this.repositorySettingsReader = repositorySettingsReader;
    }

    @Override
    public List<BCheck> loadAllItems() {
        Repository<BCheck> repository =  switch (repositorySettingsReader.repositoryType()) {
            case FILESYSTEM -> fileSystemRepository;
            case GITHUB -> gitHubBCheckRepository;
        };

        return repository.loadAllItems();
    }
}
